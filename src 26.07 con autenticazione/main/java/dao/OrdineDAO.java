package dao;

import java.util.List;

import bean.Ordine;

public interface OrdineDAO {

    public Object create(Ordine bean);
    public void update(Ordine bean );
    public void delete(Ordine bean );

    public List<Ordine> find(String username);


}

