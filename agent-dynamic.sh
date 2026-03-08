app_path=app/target/app-1.0.0-shaded.jar
attacher_path=attacher/target/attacher-1.0.0-shaded.jar
agent_path=agent/target/agent-1.0.0-shaded.jar

echo "Starting app..."
java -jar $app_path &
app_pid=$!
echo "App PID: $app_pid"

echo "Starting attacher..."
java -jar $attacher_path $app_pid $agent_path
