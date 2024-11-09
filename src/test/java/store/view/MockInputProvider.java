package store.view;

public class MockInputProvider implements InputProvider{

    private final String mockInput;

    public MockInputProvider(String mockInput){
        this.mockInput = mockInput;
    }

    @Override
    public String getInput() {
        return mockInput;
    }

    @Override
    public void closeConsole() {
    }

}
