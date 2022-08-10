package vttp.ssf.selfpracweatherapp.service;

import vttp.ssf.selfpracweatherapp.model.Currency;

public interface ExchangeRepo {
    public void save(final Currency currency);
    public Currency load(final String id);
    
}
