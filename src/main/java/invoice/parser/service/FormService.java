package invoice.parser.service;

import invoice.parser.entity.Form;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import invoice.parser.dao.FormDao;

/**
 *
 * @author konstantinos
 */
@Service
public class FormService {
    
    @Autowired
    @Qualifier("MySQLForm")
    private FormDao formDao;
    
    public int addForm(int customerId, int orderId, int supplierId, int transporterId) {
        return formDao.addForm(customerId, orderId, supplierId, transporterId);
    }
    
    public Form getFormById(int id) {
        return formDao.getFormById(id);
    }
    
    public List<Form> getForms() {
        return formDao.getForms();
    }
    
}
