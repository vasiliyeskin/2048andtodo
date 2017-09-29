package com.example.big_appls;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.data.*;
import com.vaadin.ui.themes.ValoTheme;

public class TodoLoyout extends HorizontalLayout {

    private final CheckBox checkBox;
    private final TextField textField;

    public TodoLoyout(Todo todo, TodoChangeListener changeListener) {
        setSpacing(true);
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        checkBox = new CheckBox();
        textField = new TextField();
        textField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        textField.setValueChangeMode(ValueChangeMode.BLUR);
        //textField.setWidth("100%");

        Binder<Todo> binder = new Binder<>(Todo.class);
        //Binds fields in this class to those in Todo based on their names
        //binder.bindInstanceFields(this);
        // The following does the same more explicitly
         binder.bind(textField, Todo::getText, Todo::setText);
         binder.bind(checkBox, Todo::isDone, Todo::setDone);

        binder.setBean(todo);

        addComponents(checkBox, textField);
        setExpandRatio(textField, 1);


        binder.addValueChangeListener(event -> changeListener.todoChange(todo));
/*        Arrays.asList(checkBox, textField).forEach(field -> field.addValueChangeListener(change -> changeListener
                .todoChange(todo)));*/

/*        setWidth("100%");
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        checkBox = new CheckBox();
        textField = new TextField();
        textField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        textField.setValueChangeMode(ValueChangeMode.BLUR);

        Binder<Todo> binder = new Binder<>(Todo.class);
        //Binds fields in this class to those in Todo based on their names
        binder.bindInstanceFields(this);
        // The following does the same more explicitly
        // binder.bind(text, Todo::getText, Todo::setText);
        // binder.bind(done, Todo::isDone, Todo::setDone);

        binder.setBean(todo);

        addComponent(checkBox);
        addComponentsAndExpand(textField);

        binder.addValueChangeListener(event -> changeListener.todoChange(todo));*/
    }
}
