# Question 1
print("\n======= The testing answer for question 1 =======")
def longorshort(list):
    for item in list:                   # Traverse the given list for following test
        if len(item) > 5:
            print("It is a long word.")
        else:
            print("It is a short word.")

my_list = ["FINTECH", "is", "so", "important"]      # Examples for testing
longorshort(my_list)


# Question 2
print("\n======= The testing answer for question 2 =======")
def scoretograde(score):
    grade = None                        # Initialize the variable to N.A. for later printing use
    if score >= 80:
        grade = "A"
    elif score >= 70:
        grade = "B"
    elif score >= 60:
        grade = "C"
    else:
        grade = "F"

    print(f"The grade corresponding to score: {score} is {grade}.")

my_score = [100, 80, 76, 68, 59, 0]      # Examples for testing
for score in my_score:
    scoretograde(score)


# Question 3
print("\n======= The testing answer for question 3 =======")
def classifyme(list):
    for item in list:                   # Traverse the list
        sign = None
        if item > 0:
            sign = "Positive"
        elif item == 0:
            sign = "Zero"
        else:
            sign = "Negative"
        print(f"{sign}")

my_score = [2, -5, 0, 10, -3, 1, 0]      # Examples for testing
classifyme(my_score)


# Question 4
print("\n======= The testing answer for question 4 =======")
def greater(list):
    for item in list:                   # Traverse the list
        if item < 3:
            print("less than 3")
        else:
            print("greater than or equal to 3")

my_comparison_list = [1, 2, 3, 5, 7]      # Examples for testing
greater(my_comparison_list)