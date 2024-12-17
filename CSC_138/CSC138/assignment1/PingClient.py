from socket import *
import time

serverName = 'localhost'
serverPort = 12000
clientSocket = socket(AF_INET, SOCK_DGRAM)

for x in range (10):
    # Generate ping message: Ping sequence_number current_time
    message = 'Ping ' + str(x + 1) + ' ' + time.strftime("%H:%M:%S")
    
    try:
        # Send message to local UDP server, save the send time
        send_time_s = time.time()
        clientSocket.sendto(message.encode(), (serverName, serverPort))
        
        # If message is received, calculate time
        clientSocket.settimeout(1)
        modifiedMessage, serverAddress = clientSocket.recvfrom(1024)
        recv_time_s = time.time()
        rtt_s = round(recv_time_s - send_time_s, 7)
        print('The received message is: ', modifiedMessage.decode(), 
              '  ||   RTT: ', rtt_s, "seconds")
    except TimeoutError:
        # Otherwise, print time out
        print("-- Request timed out --")

clientSocket.close()