import com.google.gson.Gson;
import model.CustomerOrder;
import org.apache.kafka.streams.processor.AbstractProcessor;

public class CustomerOrderProcessor extends AbstractProcessor<String, String> {

    private int loyaltyPoint = 100;
    private double priceLimit = 999;
    Gson g = new Gson();

    public CustomerOrderProcessor() {
    }

    @Override
    public void process(String key, String orderinfo) {
        CustomerOrder order = g.fromJson(orderinfo, CustomerOrder.class);
        System.out.println("Key :" + key + "| Transaction Info" + order.toString());
        if(order.getItemInfo().toLowerCase().contains("iphone")){
            order.setLoyaltyPoint(order.getLoyaltyPoint()+loyaltyPoint);
            if(order.getItemPrice()>priceLimit)
                order.setLoyaltyPoint(order.getLoyaltyPoint()+loyaltyPoint);
        }
        else{
            order.setLoyaltyPoint(loyaltyPoint/10);
        }
        context().forward(order.getCustID(), order.toString());
    }

}
