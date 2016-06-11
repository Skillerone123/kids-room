package ua.softserveinc.tc.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.softserveinc.tc.constants.model.AdminConst;
import ua.softserveinc.tc.dto.ConfigurationDto;
import ua.softserveinc.tc.server.exception.ResourceNotFoundException;
import ua.softserveinc.tc.util.ApplicationConfigurator;
import ua.softserveinc.tc.validator.ConfigValidator;

/**
 * Created by Nestor on 11.06.2016.
 * Handles changes to global environment settings made by Administrator
 */

@Controller
public class ConfigurationController {
    @Autowired
    private ApplicationConfigurator appConfig;

    @Autowired
    private ConfigValidator configValidator;

    @RequestMapping(value = "/adm-config", method = RequestMethod.GET)
    public String getConfiguration(Model model){
        model.addAttribute(AdminConst.ATR_CONFIG, appConfig.getObjectDto());
        return AdminConst.EDIT_CONFIG;
    }

    @RequestMapping(value = "/adm-config", method = RequestMethod.POST)
    public String setConfiguration(@ModelAttribute(value = AdminConst.ATR_CONFIG) ConfigurationDto cDto,
                                   BindingResult bindingResult){
        configValidator.validate(cDto, bindingResult);
        if(bindingResult.hasErrors()){
            return AdminConst.EDIT_CONFIG;
        }
        appConfig.acceptConfiguration(cDto);
        return AdminConst.EDIT_CONFIG;
    }
}