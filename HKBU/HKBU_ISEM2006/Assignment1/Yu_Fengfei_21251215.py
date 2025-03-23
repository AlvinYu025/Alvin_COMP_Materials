# ====================== Question 1 ======================
def longorshort(list):
    # Classifies words in a list as long/short based on character length.
    # Args:
    #     list (list[str]): List of strings to be classified
    # Outputs:
    #     Prints classification for each word
    # Criteria:
    #     - Long word: >5 characters
    #     - Short word: ≤5 characters

    for item in list:
        if len(item) > 5:
            print("It is a long word.")
        else:
            print("It is a short word.")

print("\n======= The testing answer for question 1 =======")
my_list = ["FINTECH", "is", "so", "important"]      # Examples for testing
longorshort(my_list)


# ====================== Question 2 ======================
def scoretograde(score):
    # Converts numerical score to letter grade according to predefined scale.
    # Args:
    #     score (int): Integer value between 0-100
    # Returns:
    #     Prints formatted string with score-grade correspondence
    # Grading Scale:
    #     A: 80-100
    #     B: 70-79
    #     C: 60-69
    #     F: Below 60
    # Note:
    #     Assumes valid integer input within 0-100 range

    grade = None
    if score >= 80:
        grade = "A"
    elif score >= 70:
        grade = "B"
    elif score >= 60:
        grade = "C"
    else:
        grade = "F"
    print(f"The grade corresponding to score: {score} is {grade}.")

print("\n======= The testing answer for question 2 =======")
my_score = [100, 80, 76, 68, 59, 0]      # Examples for testing
for score in my_score:
    scoretograde(score)


# ====================== Question 3 ======================
def classifyme(list):
    # Determines numerical sign classification for list elements.
    # Args:
    #     list (list[int/float]): List of numerical values
    # Outputs:
    #     Prints classification for each number:
    #     - "Positive" for values >0
    #     - "Zero" for values ==0
    #     - "Negative" for values <0

    for item in list:
        sign = None
        if item > 0:
            sign = "Positive"
        elif item == 0:
            sign = "Zero"
        else:
            sign = "Negative"
        print(f"{sign}")

print("\n======= The testing answer for question 3 =======")
my_score = [2, -5, 0, 10, -3, 1, 0]      # Examples for testing
classifyme(my_score)


# ====================== Question 4 ======================
def greater(list):
    # Compares numerical values against threshold value of 3.
    # Args:
    #     list (list[int/float]): List of numerical values
    # Outputs:
    #     Prints comparison result for each element:
    #     - "less than 3" for values <3
    #     - "greater than or equal to 3" for values ≥3
    # Note:
    #     Boundary condition includes 3 itself

    for item in list:
        if item < 3:
            print("less than 3")
        else:
            print("greater than or equal to 3")

print("\n======= The testing answer for question 4 =======")
my_comparison_list = [1, 2, 3, 5, 7]      # Examples for testing
greater(my_comparison_list)

