package main.java.org.example;

import java.beans.JavaBean;


public class Widget {

    private String widgetId;
    private String ownder;
    private String label;
    private String description;
    private SubAttributes subAttributes;

    @JsonIgnore
    private String type;

    // @JsonIgnore
    // private

    public Widget(String widgetId, String ownder, String label, String description, SubAttributes subAttributes) {
        this.widgetId = widgetId;
        this.ownder = ownder;
        this.label = label;
        this.description = description;
        this.subAttributes = subAttributes;
    }




    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getOwnder() {
        return ownder;
    }

    public void setOwnder(String ownder) {
        this.ownder = ownder;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubAttributes getSubAttributes() {
        return subAttributes;
    }

    public void setSubAttributes(SubAttributes subAttributes) {
        this.subAttributes = subAttributes;
    }

    public static class SubAttributes {
        private String name;
        private String value;


        public SubAttributes(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
