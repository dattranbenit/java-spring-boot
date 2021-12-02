package jmaster.service;

import jmaster.dao.PhoneDao;
import jmaster.entity.Phone;
import jmaster.model.PhoneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public interface PhoneService {
    void insert(PhoneDTO phone);

    void update(PhoneDTO phone);

    void delete(Long id);

    PhoneDTO get(Long id);

    List<PhoneDTO> getByUserId(Long id);

    List<PhoneDTO> search(String findName, int start, int length);

    List<PhoneDTO> getAll();
}


@Transactional
@Service
class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDao phoneDao;

    @Override
    public void insert(PhoneDTO phoneDTO) {
        Phone phone = new Phone();
        phone.setId(phoneDTO.getId());
        phone.setNumber(phoneDTO.getNumber());
        phone.setUser(phoneDTO.getUser());
        phoneDao.insert(phone);
    }

    @Override
    public void update(PhoneDTO phoneDTO) {
        Phone phone = phoneDao.get(phoneDTO.getId());
        if (phone != null) {
            phone.setId(phoneDTO.getId());
            phone.setNumber(phoneDTO.getNumber());
            phone.setUser(phoneDTO.getUser());
            phoneDao.update(phone);
        }
    }

    @Override
    public void delete(Long id) {
        Phone phone = phoneDao.get(id);
        if (phone != null) {
            phoneDao.delete(phone);
        }
    }

    @Override
    public PhoneDTO get(Long id) {
        Phone phone = phoneDao.get(id);
        return convert(phone);
    }

    @Override
    public List<PhoneDTO> getByUserId(Long id) {
        List<Phone> phones = phoneDao.getByUserId(id);
        return convertToList(phones);
    }

    @Override
    public List<PhoneDTO> search(String name, int start, int length) {
        List<Phone> phones = phoneDao.search(name, start, length);
        List<PhoneDTO> phoneDTOS = new ArrayList<PhoneDTO>();
        for (Phone phone : phones) {
            phoneDTOS.add(convert(phone));
        }
        return phoneDTOS;
    }

    @Override
    public List<PhoneDTO> getAll() {
        List<Phone> phones = phoneDao.getAll();
        List<PhoneDTO> phoneDTOS = new ArrayList<PhoneDTO>();
        for (Phone phone : phones) {
            phoneDTOS.add(convert(phone));
        }
        return phoneDTOS;
    }

    private PhoneDTO convert(Phone phone) {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(phone.getId());
        phoneDTO.setNumber(phone.getNumber());
        phoneDTO.setUser(phone.getUser());
        return phoneDTO;
    }

    private List<PhoneDTO> convertToList(List<Phone> phones) {
        List<PhoneDTO> phoneDTOS = new ArrayList<PhoneDTO>();
        for (Phone phone : phones) {
            phoneDTOS.add(convert(phone));
        }
        return phoneDTOS;
    }
}
