

JC = javac

JFLAGS = -g
WARN = -Wall



main: RSAtest.class RSAmessageServer.class RSAmessageClient.class 

RSAtest.class: RSAtest.java RSA.java
	$(JC) $(JFLAGS) RSAtest.java

RSAmessageServer.class: RSAmessageServer.java RSA.java
	$(JC) $(JFLAGS) RSAmessageServer.java

RSAmessageClient.class: RSAmessageClient.java 
	$(JC) $(JFLAGS) RSAmessageClient.java

test: RSAtest.class RSA.class
	java RSAtest

server: RSAmessageServer.class
	java RSAmessageServer 10625

client: RSAmessageClient.class
	java RSAmessageClient localhost 10625

clean:
	$(RM) *.class


