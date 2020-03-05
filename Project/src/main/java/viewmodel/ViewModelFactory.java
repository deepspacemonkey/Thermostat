package viewmodel;

import model.HeaterModel;

public class ViewModelFactory {
    private MainViewModel mainViewModel;

    public ViewModelFactory(HeaterModel model) {
        mainViewModel = new MainViewModel(model);
    }

    public MainViewModel getHeaterViewModel() {
        return mainViewModel;
    }
}
