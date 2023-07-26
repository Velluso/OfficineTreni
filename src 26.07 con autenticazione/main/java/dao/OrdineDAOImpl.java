package dao;

import bean.Ordine;

public class OrdineDAOImpl extends BaseDAO implements OrdineDAO {

    public Object create(Ordine bean){
        return super.create(bean);
    }

    public void update(Ordine bean ){
        super.update(bean);
    }

    public void delete(Ordine bean ){	//da implementare
        super.update(bean);
    }

    public Ordine find(Integer id) {
        return (Ordine) super.find(Ordine.class,id);
    }


}

