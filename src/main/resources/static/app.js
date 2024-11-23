let socket;

function connectWebSocket() {
  socket = new WebSocket("ws://localhost:8080/teste");
  socket.addEventListener("open", handleSocketOpen);
  socket.addEventListener("error", handleSocketError);
  socket.addEventListener("close", handleSocketClose);
  socket.onmessage = (event) => {
    updateLiveChat(event.data);
  };
}

function handleSocketError(error) {
  console.error("Erro no websocket:", error);
}

function handleSocketClose() {
  console.log("Websocket fechado.");
}

function handleSocketOpen() {
  console.log("Websocket conectado.");
}

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  } else {
    $("#conversation").hide();
  }
}

function connect() {
  setConnected(true);
  connectWebSocket();
}

function disconnect() {

  socket.close();
  setConnected(false);

  console.log("Disconnected");
}

function sendMessage() {

  const message = {
    user: $("#user").val(),
    message: $("#message").val(),
  };

  socket.send(JSON.stringify(message));

  $("#message").val("");

}

function updateLiveChat(message) {
  $("#livechat").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
  $("form").on("submit", (e) => e.preventDefault());
  $("#connect").click(() => connect());
  $("#disconnect").click(() => disconnect());
  $("#send").click(() => sendMessage());
});
