import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class OrderManagementProcessorAPI {

    public static void main(String[] args) throws InterruptedException {

        Topology topology = new Topology();
        StreamsConfig streamsConfig = new StreamsConfig(getProperties());
        Serde<String> stringSerde = Serdes.String();
        Deserializer<String> stringDeserializer = stringSerde.deserializer();
        Serializer<String> stringSerializer = stringSerde.serializer();

        String sourceTopic = "TransactionInfo";
        String sinkTopic = "CustomerLoyaltyInfo";

        CustomerOrderProcessor processor = new CustomerOrderProcessor();

        topology.addSource("transactionSourceNode", stringDeserializer, stringDeserializer, sourceTopic)
                .addProcessor("transactionProcessor", () -> processor, "transactionSourceNode")
                .addSink("loyaltySink", sinkTopic, stringSerializer, stringSerializer, "transactionProcessor");

        KafkaStreams streams = new KafkaStreams(topology, streamsConfig);
        System.out.println(topology.describe());
        final CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });
        try {
            streams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
        System.exit(0);
    }

    private static Properties getProperties(){
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "Order_Management_Application-2");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return properties;
    }
}
