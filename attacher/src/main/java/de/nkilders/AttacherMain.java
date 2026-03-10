package de.nkilders;

import module jdk.attach;

public class AttacherMain {

  public static void main(String[] args) throws Exception {
    IO.println("[Attacher] main() called");

    var appPid = args[0];
    var agentPath = args[1];

    var jvm = VirtualMachine.attach(appPid);
    jvm.loadAgent(agentPath, "some dynamic args");
    jvm.detach();
  }
}
