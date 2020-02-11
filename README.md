# command
java method command builder

!!!  UNFINISHED  !!!

goal
create command builder that can serch methods annotated with CommandMethod.class.
create commandExecutor which manages the correctness of input arguments and calls correct Method to invoke from Strin input

usage
it may look like this {
CommandBuilder cb = new CommandBuilder();
cb.add(new BuildCommandOptions("/command"));

Foo foo = new Foo();
cb.feed(foo);
cb.feed(Foo.class);

CommandExecutor = cb.build();
cb.process(String commandWithArgs , Object... initArgs);
}

it will look like this below
class Foo {

    // passed command /hello
    @CommandMethod(command = "hello")
    public void hello(String whoSend) { }
    
    // passed command /hello 'message'
    @CommandMethod(command = "hello")
    public void hello(String whoSend , String message) { }    
    
    // passed command /hello 'message'
    @CommandMethod(command = "hello")
    public void hello(String whoSend , int count) { }    
    
    // passed command /todo 
    @CommandMethod(command = "hello")
    public void hello(Account acc) { }    
}
