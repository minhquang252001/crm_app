package testservlet.Service;

import java.util.List;

import testservlet.entity.RoleEntity;
import testservlet.repository.RoleRepository;

public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();
	
	public boolean insert(String roleName,String roleDesc) {
		int count = roleRepository.insert(roleName, roleDesc);
		
		return count > 0;
	}
	public List<RoleEntity> getAllRole(){
		return roleRepository.findAll();
	}
	public boolean deleteRole(int id) {
		return roleRepository.deleteById(id) > 0;
	}
	public boolean updateRole(String roleName,String roleDesc,int id) {
		int count = roleRepository.updateById(roleName, roleDesc, id);
		return count > 0;
	}
	public List<RoleEntity> getOneRole(int id){
		return roleRepository.findByOne(id);
	}
}
