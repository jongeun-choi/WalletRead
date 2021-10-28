package com.clonecoin.walletread.adaptor;


import com.clonecoin.walletread.config.KafkaProperties;
import com.clonecoin.walletread.domain.event.AnalysisDTO;
import com.clonecoin.walletread.service.Impl.WalletServiceImpl;
import com.clonecoin.walletread.service.LeaderInformation;
import com.clonecoin.walletread.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AnalysisConsumer {
    private final Logger log = LoggerFactory.getLogger(AnalysisConsumer.class);

    private final AtomicBoolean closed = new AtomicBoolean(false);

    public static final String TOPIC_ANALYSIS = "buy-sell";

    private final KafkaProperties kafkaProperties;

    private KafkaConsumer<String, String> kafkaConsumer;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public AnalysisConsumer(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Autowired
    private LeaderInformation leaderInformation;

    @PostConstruct
    public void start(){
        log.info("Kafka consumer starting ...");
        this.kafkaConsumer = new KafkaConsumer<>(kafkaProperties.getConsumerProps());
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        // subscribe 할 토픽명을 지정
        kafkaConsumer.subscribe(Collections.singleton(TOPIC_ANALYSIS)); // analysis 서버 구독

        log.info("Analysis consumer started");

        executorService.execute(()-> {
                    try {

                        while (!closed.get()){
                            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(3));
                            for(ConsumerRecord<String, String> record: records) {
                                log.info("\n\nConsumer Message Arrive");

                                ObjectMapper objectMapper = new ObjectMapper();

                                    log.info("\n\nConsumed message in {} : {}", TOPIC_ANALYSIS, record.value());
                                    AnalysisDTO analysisDTO = objectMapper.readValue(record.value(), AnalysisDTO.class);
                                    
                                    // 코인의 최신 정보를 가지고와서 갱신화 해주는 로직
                                    leaderInformation.updateCoin(analysisDTO);

                            }
                        }
                        kafkaConsumer.commitSync();
                    }catch (WakeupException e){
                        if(!closed.get()){
                            throw e;
                        }

                    }catch (Exception e){
                        log.error(e.getMessage(), e);
                    }finally {
                        log.info("kafka consumer close");
                        kafkaConsumer.close();
                    }
                }
        );
    }

    public KafkaConsumer<String, String> getKafkaConsumer() {
        return kafkaConsumer;
    }

    public void shutdown() {
        log.info("Shutdown Kafka consumer");
        closed.set(true);
        kafkaConsumer.wakeup();
    }


}

 
