package by.barilo.tgbotsservices.services;

import by.barilo.tgbotsservices.objects.Module;

public class ModuleLoader {
    public static Module getById(String id) {
        return Module.builder().id(id).build();
    }
}
