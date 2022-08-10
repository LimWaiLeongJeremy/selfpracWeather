package vttp.ssf.selfpracweatherapp.model;

import java.util.UUID;

public class Currency {
    private Query query;
    private String result;
    private String susccess;
    private final String id = UUID
                        .randomUUID()
                        .toString()
                        .substring(0, 8);
    public Query getQuery() {
        return query;
    }
    public void setQuery(Query query) {
        this.query = query;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getSusccess() {
        return susccess;
    }
    public void setSusccess(String susccess) {
        this.susccess = susccess;
    }
    public String getId() {
        return id;
    }
}
