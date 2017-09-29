package com.example.big_appls;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;


@SpringUI
@Theme("valo")
public class BIG_UI extends UI{
    private VerticalLayout layout;
    private Focusable focusElement;

    private Model model;
    private Controller controller;

    private final CustomerRepository repo;
    private final CustomerEditor editor;
    final Grid<Customer> grid;
    final TextField filter;
    private final Button addNewBtn;

    @Autowired
    GameField vv;

    @Autowired
    TodoList todoList;

    @Autowired
    public BIG_UI(CustomerRepository repo, CustomerEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Customer.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer", FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout1();
        addHeader1();
        addForm1();

        addHeader();
        addForm();
        addTodoList();
        addActionButton();





        Label header = new Label("CRUD");
        header.addStyleName(ValoTheme.LABEL_H1);
        header.setSizeUndefined();
        layout.addComponent(header);
        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        layout.addComponents(actions, grid, editor);

        grid.setHeight(300, Unit.PIXELS);
        grid.setWidth("70%");
        grid.setColumns("id", "firstName", "lastName", "email");
//        grid.setEnabled(true);

        filter.setPlaceholder("Filter by last name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editCustomer(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "", "")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });

        // Initialize listing
        listCustomers(null);
    }

    // tag::listCustomers[]
    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        } else {
            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
    // end::listCustomers[]





    private void setupLayout1() {
        layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private void addHeader1() {
        Label header = new Label("2048");
        header.addStyleName(ValoTheme.LABEL_H1);
        header.setSizeUndefined();
        layout.addComponent(header);
    }

    private void addForm1() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.setWidth("80%");

        model = vv.getModel();
        controller = vv.getController();

        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
        horizontalLayout2.setSpacing(true);
        horizontalLayout2.setWidth(500, Unit.PIXELS);

        // add control buttons
        Button button_l = new Button();
        button_l.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button_l.setClickShortcut(ShortcutAction.KeyCode.ARROW_LEFT);
        button_l.setIcon(FontAwesome.ARROW_LEFT);
        button_l.addClickListener(e ->
                vv.keyPress(ShortcutAction.KeyCode.ARROW_LEFT));

        Button button_r = new Button();
        button_r.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button_r.setClickShortcut(ShortcutAction.KeyCode.ARROW_RIGHT);
        button_r.setIcon(FontAwesome.ARROW_RIGHT);
        button_r.addClickListener(e ->
                vv.keyPress(ShortcutAction.KeyCode.ARROW_RIGHT));

        Button button_u = new Button();
        button_u.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button_u.setClickShortcut(ShortcutAction.KeyCode.ARROW_UP);
        button_u.setIcon(FontAwesome.ARROW_UP);
        button_u.addClickListener(e ->
                vv.keyPress(ShortcutAction.KeyCode.ARROW_UP));

        Button button_d = new Button();
        button_d.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button_d.setClickShortcut(ShortcutAction.KeyCode.ARROW_DOWN);
        button_d.setIcon(FontAwesome.ARROW_DOWN);
        button_d.addClickListener(e ->
                vv.keyPress(ShortcutAction.KeyCode.ARROW_DOWN));

        Button button_reset = new Button("RESET (Esc)");
        button_reset.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button_reset.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        button_reset.addClickListener(e ->
                vv.keyPress(ShortcutAction.KeyCode.ESCAPE));

        Button button_auto = new Button("AUTO (A)");
        button_auto.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button_auto.setClickShortcut(ShortcutAction.KeyCode.A);
        button_auto.addClickListener(e ->
                vv.keyPress(ShortcutAction.KeyCode.A));

        Button button_rollback = new Button("ROLLBACK (Z)");
        button_rollback.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button_rollback.setClickShortcut(ShortcutAction.KeyCode.Z);
        button_rollback.addClickListener(e ->
                vv.keyPress(ShortcutAction.KeyCode.Z));

        Button button_random = new Button("RANDOM (R)");
        button_random.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button_random.setClickShortcut(ShortcutAction.KeyCode.R);
        button_random.addClickListener(e ->
                vv.keyPress(ShortcutAction.KeyCode.R));

        horizontalLayout2.addComponent(vv);
        layout.addComponent(horizontalLayout2);

        GridLayout gridLayout = new GridLayout(3,2);
        gridLayout.addComponent(button_u, 1,0);
        gridLayout.addComponent(button_d, 1,1);
        gridLayout.addComponent(button_l, 0,1);
        gridLayout.addComponent(button_r, 2,1);

        GridLayout gridLayout2 = new GridLayout(5,2);
        gridLayout2.addComponent(button_reset, 1,1);
        gridLayout2.addComponent(button_rollback, 2,1);
        gridLayout2.addComponent(button_auto, 3,1);
        gridLayout2.addComponent(button_random, 4,1);

        horizontalLayout.addComponents(gridLayout,gridLayout2);
        layout.addComponent(horizontalLayout);
    }

    public void rerender() {
        setupLayout1();
        addHeader1();
        addForm1();
    }



    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private void addHeader() {
        Label header = new Label("TODO");
        header.addStyleName(ValoTheme.LABEL_H1);
        header.setSizeUndefined();
        layout.addComponent(header);
    }

    private void addForm() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.setWidth("80%");
        TextField textField = new TextField();
        textField.setWidth("100%");
        focusElement = textField;

        Button button = new Button();
        button.setIcon(FontAwesome.PLUS);
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);

        horizontalLayout.addComponents(textField, button);
        horizontalLayout.setExpandRatio(textField, 1);
        layout.addComponent(horizontalLayout);

        button.addClickListener(click -> {
            todoList.save(new Todo(textField.getValue()));
            Notification.show(textField.getValue());
            textField.clear();
            focusElement.focus();
        });

        textField.focus();

        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    private void addTodoList() {
        todoList.setWidth("80%");
        layout.addComponent(todoList);
    }

    private void addActionButton() {
        Button delete_completed = new Button("Delete completed");
        delete_completed.addClickListener(clicl -> {
            todoList.deleteCompleted();
            focusElement.focus();
        });
        layout.addComponent(delete_completed);
    }
}
