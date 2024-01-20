package dao;

import java.util.List;

public interface IDataAccessObject<DomainObject> {
	public void guardar(DomainObject object);
	public void borrar(DomainObject object);
	public List<DomainObject> findAll();
	public DomainObject find(String param);
}
