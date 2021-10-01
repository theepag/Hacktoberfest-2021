import speech_recognition as sr
from pynput.keyboard import Controller
import time

class Main:
    def __init__(self):
        self.board = Controller()
        print("Started!\n\n")
        while True:
            query = self.take_command()
            print("TYPING...\n")
            for i in query: 
                self.board.type(i)
                time.sleep(0.01)
            self.board.type(" ")

    def take_command(self):
        try:
            r = sr.Recognizer()
            with sr.Microphone() as source:
                r.adjust_for_ambient_noise(source)
                print("Listenting...\n")
                audio = r.listen(source)
                print("Recognizing.... \n")
                query2 = r.recognize_google(audio, language='hi-IN')
                query1 = str(query2)
                print("You Said: ", query1, "\n\n")
            return query1
        except Exception as e:
            print(e)
            return ""
    
if __name__ == "__main__":
    Main()
