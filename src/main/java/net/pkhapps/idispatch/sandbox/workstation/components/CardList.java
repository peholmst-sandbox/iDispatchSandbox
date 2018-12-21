package net.pkhapps.idispatch.sandbox.workstation.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.shared.Registration;

import javax.annotation.Nonnull;
import java.io.Serializable;

@Tag("card-list")
@HtmlImport("frontend://src/components/card-list/card-list.html")
public class CardList<C extends Component, T> extends Component {

    private final CardFactory<C, T> cardFactory;
    private final CardUpdater<C, T> cardUpdater;

    private DataProvider<T, ?> dataProvider;
    private Registration dataProviderRegistration;

    public CardList(@Nonnull CardFactory<C, T> cardFactory,
                    @Nonnull CardUpdater<C, T> cardUpdater) {
        this.cardFactory = cardFactory;
        this.cardUpdater = cardUpdater;
    }

    public void setDataProvider(DataProvider<T, ?> dataProvider) {
        if (this.dataProviderRegistration != null) {
            this.dataProviderRegistration.remove();
        }

        this.dataProvider = dataProvider;
        this.dataProviderRegistration = dataProvider.addDataProviderListener(event -> refreshAll());

        refreshAll();
    }

    // TODO Sorting
    // TODO Make container lazy
    // TODO Smart card updates

    private void refreshAll() {
        getElement().removeAllChildren();
        if (dataProvider != null) {
            dataProvider
                    .fetch(new Query<>()).map(cardFactory::createCard)
                    .forEach(card -> getElement().appendChild(card.getElement()));
        }
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
