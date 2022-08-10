
package vttp.ssf.selfpracweatherapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vttp.ssf.selfpracweatherapp.model.Currency;
import vttp.ssf.selfpracweatherapp.model.Query;

@Service
public class ExchangeSrc implements ExchangeRepo {
    
    @Autowired
    RedisTemplate<String, Currency> redisTemplate;
    
    public Currency convertCurrency(Query query) {
        // String apiKey = "";
        String apiKey = System.getenv("API_KEY");

        String currencyUrl = "https://api.apilayer.com/currency_data/convert"
                            + "?to="
                            + query.getTo()
                            + "&from="  
                            + query.getFrom()
                            + "&amount="
                            + query.getAmount();

        RestTemplate template = new RestTemplate();
        RequestEntity<Void> req = RequestEntity.get(currencyUrl)
                                .header("apikey", apiKey)
                                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<Currency> resp = template.exchange(req, Currency.class);
        resp.getBody().setQuery(query);
        return resp.getBody();

    }

    @Override
    public void save(Currency currency) {
        redisTemplate.opsForValue().set(currency.getId(), currency);
        
    }

    @Override
    public Currency load(String id) {
        Currency result = redisTemplate.opsForValue().get(id);
        return result;
    }
}
