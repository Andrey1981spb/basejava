package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ResumeSections {

    protected Map<SectionType, String> typeStringMap = new HashMap<>();
    protected List<String> typeStringList = new ArrayList<>();

    public List<String> getDataFromSection() {
        for (Map.Entry<SectionType, String> entry : typeStringMap.entrySet()) {
            typeStringList.add(entry.getValue());
        }
        return typeStringList;
    }

}