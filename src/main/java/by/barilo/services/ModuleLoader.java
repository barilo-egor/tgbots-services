package by.barilo.services;

import by.barilo.objects.Module;

public class ModuleLoader {
    public static Module getById(String id) {
        return Module.builder().id(id).build();
    }
}
