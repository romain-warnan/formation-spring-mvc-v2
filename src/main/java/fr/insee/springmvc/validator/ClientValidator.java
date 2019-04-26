package fr.insee.springmvc.validator;

import fr.insee.springmvc.model.Client;
import fr.insee.springmvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {

    @Autowired
    private ClientService clientService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(Client.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Client client = (Client) object;
        if(clientService.emailDejaUtilise(client)) {
            errors.rejectValue("email", "client.email.deja.utilise");
        }
    }
}
