package dao;

import java.util.List;

import bean.Treno;
import bean.Vagone;

public interface TrenoDAO {

    public Object create(Treno bean);
    public void update(Treno bean );
    public void delete(Treno bean );

    public Treno find(Integer id);

    public List<Vagone> vagoniDiUnTreno(String idTreno);
}

