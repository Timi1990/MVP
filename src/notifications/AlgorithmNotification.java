package notifications;

import algorithms.search.Searcher;
import model.IModel;

public class AlgorithmNotification implements ObservableNotification<Searcher> {

    private IModel model;
    private Searcher searcher;
    @Override
    public void apply() {
        searcher = model.getAlgorithm();
    }

    @Override
    public void print() {

    }

    @Override
    public void init(IModel model) {
        this.model = model;
    }

    @Override
    public Searcher getData() {
        return searcher;
    }
}
