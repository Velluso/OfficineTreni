package dao;

import bean.Ordine;

public interface OrdineDAO {

    public Object create(Ordine bean);
    public void update(Ordine bean );
    public void delete(Ordine bean );

    public Ordine find(String username);


}

