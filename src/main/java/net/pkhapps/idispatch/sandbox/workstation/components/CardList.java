package net.pkhapps.idispatch.sandbox.workstation.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.ironlist.IronList;
import com.vaadin.flow.data.binder.HasDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import javax.annotation.Nonnull;
import java.io.Serializable;

public class CardList<C extends Component, T> extends Composite<IronList<T>> implements HasDataProvider<T> {

    private final CardFactory<C, T> cardFactory;
    private final CardUpdater<C, T> cardUpdater;


    public CardList(@Nonnull CardFactory<C, T> cardFactory,
                    @Nonnull CardUpdater<C, T> cardUpdater) {
        this.cardFactory = cardFactory;
        this.cardUpdater = cardUpdater;
    }

    @Override
    protected IronList<T> initContent() {
        var ironList = new IronList<T>();
        ironList.setRenderer(new ComponentRenderer<>(cardFactory::createCard));
        ironList.getStyle().set("padding", "10px");
        return ironList;
    }

    @Override
    public void setDataProvider(DataProvider<T, ?> dataProvider) {
        getContent().setDataProvider(dataProvider);

    }

    @Nonnull
    public interface CardFactory<C extends Component, T> extends Serializable {

        @Nonnull
        C createCard(@Nonnull T item);
    }

    @Nonnull
    public interface CardUpdater<C extends Component, T> extends Serializable {

        void updateCard(@Nonnull C card, @Nonnull T item);
    }
}
