package net.pkhapps.idispatch.sandbox.workstation.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Tag("accordion-element")
@HtmlImport("frontend://src/components/accordion/accordion-element.html")
public class Accordion extends Component {

    private final List<Item> items = new ArrayList<>();
    private Item selectedItem;

    public Item addItem(@Nonnull String caption, @Nonnull Component content) {
        var item = new Item(caption, content);
        items.add(item);
        getElement().appendChild(item.getElement());
        if (selectedItem == null) {
            select(item);
        }
        return item;
    }

    @Nonnull
    public Item getSelectedItem() {
        if (selectedItem == null) {
            throw new IllegalStateException("There are no items in the accordion");
        }
        return selectedItem;
    }

    public void select(@Nonnull Item item) {
        Objects.requireNonNull(item, "item must not be null");
        if (this.selectedItem != item) {
            if (this.selectedItem != null) {
                this.selectedItem.setExpanded(false);
            }
            item.setExpanded(true);
            this.selectedItem = item;
        }
    }

    @Tag("accordion-item")
    public class Item extends Component {

        private final String caption;
        private final Component content;

        Item(@Nonnull String caption, @Nonnull Component content) {
            this.caption = Objects.requireNonNull(caption, "caption must not be null");
            this.content = Objects.requireNonNull(content, "content must not be null");
            getElement().setAttribute("caption", caption);
            getElement().appendChild(content.getElement());
            getElement().addEventListener("caption-click", event -> select(this));
        }

        @Nonnull
        public String getCaption() {
            return caption;
        }

        @Nonnull
        public Component getContent() {
            return content;
        }

        public boolean isSelected() {
            return this == getSelectedItem();
        }

        void setExpanded(boolean expanded) {
            getElement().setAttribute("expanded", expanded);
        }
    }
}
