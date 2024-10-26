package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Widget {

    private String type;
    private String requestId;
    private String widgetId;
    private String owner;
    private String label;
    private String description;
    private List<OtherAttribute> otherAttributes;

    @JsonIgnore
    private String field;

    public Widget(String type, String requestId, String widgetId, String owner, String label,
                  String description, List<OtherAttribute> otherAttributes, String field) {
        this.type = type;
        this.requestId = requestId;
        this.widgetId = widgetId;
        this.owner = owner;
        this.label = label;
        this.description = description;
        this.otherAttributes = otherAttributes;
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public List<OtherAttribute> getOtherAttributes() {
        return otherAttributes;
    }

    public void setOtherAttributes(List<OtherAttribute> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public static class OtherAttribute {
        private String name;
        private String value;

        public OtherAttribute(String name, String value) {
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
