package vttp.ssf.selfpracweatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.ssf.selfpracweatherapp.model.Currency;
import vttp.ssf.selfpracweatherapp.model.Query;
import vttp.ssf.selfpracweatherapp.service.ExchangeSrc;

@Controller
@RequestMapping (path = "/exchange")
public class CurrencyController {

    @Autowired
    private ExchangeSrc service;

    @GetMapping
    public String exchange(@RequestParam(required = true) String to
                            ,@RequestParam(required = true) String from
                            ,@RequestParam(required = true) String amount
                            ,Model model
                            ,@ModelAttribute Currency c) {
    Query q = new Query();
    q.setTo(to);
    q.setFrom(from);
    q.setAmount(amount);
    Currency cur = service.convertCurrency(q);
    model.addAttribute("currency", cur);
    service.save(cur);
    return null;    
    }

    @GetMapping ("/{id}")
    public String getExchangeRecord (Model model
                                    ,@PathVariable(value = "id") String id) {
        Currency c = service.load(id);
        model.addAttribute("currency", c);
        return "exchange";
    }
}
