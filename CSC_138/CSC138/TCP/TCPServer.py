from socket import *

serverPort = 12000
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))
serverSocket.listen(1)

print('The server is ready to receive')

while True:
    connection, address = serverSocket.accept()
    message = connection.recv(1024).decode()
    modifiedMessage = message.upper()
    connection.send(modifiedMessage.encode())
    connection.close()