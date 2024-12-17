from socket import *

serverName = '127.0.0.1'
serverPort = 12000
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort))

sentence = input("Please enter a sentence in lower case: ")

clientSocket.send(sentence.encode())
modifiedSentence = clientSocket.recv(1024)

print("This is an upper case sentence: ", modifiedSentence.decode())
clientSocket.close()