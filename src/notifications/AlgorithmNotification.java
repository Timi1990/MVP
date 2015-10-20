package notifications;

import algorithms.search.Searcher;
import model.IModel;

public class AlgorithmNotification implements ObservableNotification<Searcher>
{
    private IModel model;
    private Searcher searcher;

    @Override
    public void apply()
    {
        searcher = model.getAlgorithm();
    }

    @Override
    public void print()
    {
        System.out.print(searcher.toString());
    }

    @Override
    public void init(IModel model)
    {
        this.model = model;
    }

    @Override
    public Searcher getData()
    {
        return searcher;
    }

    @Override
    public void setData(Searcher data)
    {
        this.searcher = data;
    }
}
