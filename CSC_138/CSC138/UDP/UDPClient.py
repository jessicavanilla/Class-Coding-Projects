from socket import *

serverName = 'localhost'
serverPort = 12000
clientSocket = socket(AF_INET, SOCK_DGRAM)

message = input('Please enter your message in lower case format: ')

clientSocket.sendto(message.encode(), (serverName, serverPort))
modifiedMessage, serverAddress = clientSocket.recvfrom(1024)

print('The capitalized sentence is: ', modifiedMessage.decode())
clientSocket.close()