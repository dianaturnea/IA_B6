__author__ = "Nichi Hash"



file1 = open ("file1.txt")  #file containing image names
file2 = open ("file2.txt")  #file containing image scores


images = file1.readlines()
feelings = file2.readlines()

def acceptance ():
    return "acceptance"

def admiration ():
    return "admiration"

def amazement ():
    return "amazement"

def anger ():
    return "anger"

def annoyance ():
    return "annoyance"

def anticipation ():
    return "anticipation"

def boredom ():
    return "boredom"

def disgust ():
    return "disgust"

def ecstasy ():
    return "ecstasy"

def fear ():
    return "fear"

def grief ():
    return "grief"

def interest ():
    return "interest"

def joy ():
    return "joy"

def rage ():
    return "rage"

def sadness ():
    return "sadness"

def serenity ():
    return "serenity"

def surprise ():
    return "surprise"

def terror ():
    return "terror"

def trust ():
    return "trust"

def neutral ():
    return "neutral"

parsefeeling = {
    1 : acceptance,
    2 : admiration,
    3 : amazement,
    4 : anger,
    5 : annoyance,
    6 : anticipation,
    7 : boredom,
    8 : disgust,
    9 : ecstasy,
    10 : fear,
    11 : grief,
    12 : interest,
    13 : joy,
    14 : rage,
    15 : sadness,
    16 : serenity,
    17 : surprise,
    18 : terror,
    19 : trust,
    20 : neutral
}





for i in range(images.__len__()): #both files are supposed to have equal numbers of lines
    feel = int(feelings[i])
    print images[i], parsefeeling[feel]()
