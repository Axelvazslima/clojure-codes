import time
import numpy as np
import os


def create_table() -> list:
    return [[0, 0, 0], [0, 0, 0], [0, 0, 0]]


def update_table(old_table, players_play_line, players_play_column, player) -> None:
    table = np.copy(old_table)
    table[players_play_line][players_play_column] = player
    return table


def welcome_message() -> str:
    return f"The game is starting. Input two numbers separeted by whitespaces from 1 to 3.\nThe first number is the line, the second one is the column. For example: 1 3 means line 1, column 3.\nTo finish the game input anything other than a number.\nGood luck!\n"


def game_ui(table) -> str:
    out = "Current game table:"
    out += "\n"
    for i in range(len(table)):
        for j in range(len(table[i])):
            if j == 0:
                out += str(i) + "> "
            if j > 0:
                out += " | "
            if table[i][j] == 1:
                out += "X"
            elif table[i][j] == 2:
                out += "O"
            else:
                out += "▢"
        out += "\n"
    out += "   ----------\n"
    out += "+> 0 | 1 | 2"
    return out


def handle_users_input(table, player) -> list:
    user_io = input(
        f"\nInput where you want {'X' if player == 1 else 'O'} to be placed: ").strip().split()
    finish_game = not user_io[0].isdigit()
    if finish_game:
        return -1
    user_io = [int(x) for x in user_io]
    condition_in_outer_bounds = (0 <= user_io[0] <= 2)
    condition_in_inner_bounds = (0 <= user_io[1] <= 2)
    conditions = condition_in_inner_bounds and condition_in_outer_bounds and table[
        user_io[0]][user_io[1]] == 0
    while not conditions:
        user_io = input(
            "Input the correct way -> 'Line Column': ").strip().split()
        finish_game = not user_io[0].isdigit()
        if finish_game:
            return -1
        user_io = [int(x) for x in user_io]
        condition_in_outer_bounds = (0 <= user_io[0] <= 2)
        condition_in_inner_bounds = (0 <= user_io[1] <= 2)
        conditions = condition_in_inner_bounds and condition_in_outer_bounds and table[
            user_io[0]][user_io[1]] == 0
    return user_io


def print_winner(scoreboard) -> str:
    points_diff = abs(scoreboard[1] - scoreboard[2])
    out = f"Finishing {points_diff} point{'s' if points_diff > 1 else ''} ahead, player "
    if scoreboard[1] > scoreboard[2]:
        out += "X is the winner"
    elif scoreboard[1] < scoreboard[2]:
        out += "O is the winner!"
    else:
        out = "It's a tie!"
    return out


def check_winner_in_column(table, column, element) -> bool:
    for i in range(len(table)):
        if table[i][column] != element:
            return False
    return True


def check_winner_in_line(table, line, element) -> bool:
    for i in range(len(table[line])):
        if table[line][i] != element:
            return False
    return True


def check_winner_in_diags(table, element) -> bool:
    left, right = True, True
    for i in range(len(table)):
        if table[i][i] != element:
            left = False
            break
    j = 0
    for i in range(len(table) - 1, -1, -1):
        if table[i][j] != element:
            right = False
            break
        j += 1
    is_winner = right or left
    return is_winner


def is_there_a_winner(table, line, column) -> int:
    element = table[line][column]
    cond_lines = check_winner_in_line(table, line, element)
    cond_columns = check_winner_in_column(table, column, element)
    cond_diag = check_winner_in_diags(table, element)
    if cond_lines or cond_columns or cond_diag:
        return element
    return None


def clear_screen() -> None:
    os.system("clear")


def game_loop(counter, table, players, is_full) -> int:
    while is_full < 9:
        player = players[counter % 2]
        user_input = handle_users_input(table, player)

        if user_input == -1:
            return user_input

        table = update_table(table, user_input[0], user_input[1], player)

        winner_or_not = is_there_a_winner(table, user_input[0], user_input[1])
        clear_screen()
        print(game_ui(table))
        if winner_or_not:
            return winner_or_not
        is_full += 1

        counter += 1
    return 0


def every_round_message(winner, scoreboard):
    return f"\nPlayer {'X' if winner == 1 else 'O'} won!\nThe score is:\nPlayer 1: {scoreboard[1]}\nPlayer 2: {scoreboard[2]}\nTies: {scoreboard[0]}\n"


def start_game(counter, players, scores) -> None:
    clear_screen()
    table = create_table()
    print(welcome_message())
    print(game_ui(table))
    any_winner = game_loop(counter, table, players, 0)
    if any_winner != -1:
        scores[any_winner] += 1
        print(every_round_message(any_winner, scores))
        time.sleep(2)
        start_game(counter, players, scores)


def game():
    counter = 0
    players = [1, 2]
    scoreboard = [0, 0, 0]
    start_game(counter, players, scoreboard)
    print(print_winner(scoreboard))


def main() -> None:
    game()


main()
