package testservlet.Service;

import java.util.List;

import testservlet.entity.MenberEntity;
import testservlet.entity.RoleEntity;
import testservlet.repository.MenberRepository;

public class MenberService {

	private MenberRepository menberRepository = new MenberRepository();
	public List<MenberEntity> getAllMenber(){
		return menberRepository.findAllMenber();
	}
	public List<RoleEntity> getOneNameRole(){
		return menberRepository.findNameRole();
	}
	
	public boolean inserMenber(String firstname,String lastname,String username , int roleuser) {
		int count = menberRepository.insertMenber(firstname, lastname, username, roleuser);
		return count > 0;
	}
	public boolean deleteMenber(int id) {
		int count = menberRepository.deleteMenber(id);
		return count > 0;
	}
	public List<MenberEntity> getFindByUpdate(int id){
		return menberRepository.findByMenBer(id);
	}
	
	public boolean getUpdateMenber(int id, String firstname,String lastname,String username,int roleUser) {
		int count = menberRepository.updateMenBer(id, firstname, lastname, username, roleUser);
		return count > 0;
	}
}
