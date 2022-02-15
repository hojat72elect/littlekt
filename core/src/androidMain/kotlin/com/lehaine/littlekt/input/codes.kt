package com.lehaine.littlekt.input

import android.view.KeyEvent

internal const val UNKNOWN_KEY = KeyEvent.KEYCODE_UNKNOWN

internal val Key.keyCode: Int
    get() {
        return when (this) {
            Key.ANY_KEY -> UNKNOWN_KEY
            Key.BACKSPACE -> KeyEvent.KEYCODE_BACK
            Key.TAB -> KeyEvent.KEYCODE_TAB
            Key.ENTER -> KeyEvent.KEYCODE_ENTER
            Key.SHIFT_LEFT -> KeyEvent.KEYCODE_SHIFT_LEFT
            Key.CTRL_LEFT -> KeyEvent.KEYCODE_CTRL_LEFT
            Key.ALT_LEFT -> KeyEvent.KEYCODE_ALT_LEFT
            Key.SHIFT_RIGHT -> KeyEvent.KEYCODE_SHIFT_RIGHT
            Key.CTRL_RIGHT -> KeyEvent.KEYCODE_CTRL_RIGHT
            Key.ALT_RIGHT -> KeyEvent.KEYCODE_ALT_RIGHT
            Key.PAUSE_BREAK -> KeyEvent.KEYCODE_BREAK
            Key.CAPS_LOCK -> KeyEvent.KEYCODE_CAPS_LOCK
            Key.ESCAPE -> KeyEvent.KEYCODE_ESCAPE
            Key.PAGE_UP -> KeyEvent.KEYCODE_PAGE_UP
            Key.SPACE -> KeyEvent.KEYCODE_SPACE
            Key.PAGE_DOWN -> KeyEvent.KEYCODE_PAGE_DOWN
            Key.END -> KeyEvent.KEYCODE_MOVE_END
            Key.HOME -> KeyEvent.KEYCODE_HOME
            Key.ARROW_LEFT -> KeyEvent.KEYCODE_DPAD_LEFT
            Key.ARROW_UP -> KeyEvent.KEYCODE_DPAD_UP
            Key.ARROW_RIGHT -> KeyEvent.KEYCODE_DPAD_RIGHT
            Key.ARROW_DOWN -> KeyEvent.KEYCODE_DPAD_DOWN
            Key.PRINT_SCREEN -> UNKNOWN_KEY
            Key.INSERT -> KeyEvent.KEYCODE_INSERT
            Key.DELETE -> KeyEvent.KEYCODE_DEL
            Key.NUM0 -> KeyEvent.KEYCODE_0
            Key.NUM1 -> KeyEvent.KEYCODE_1
            Key.NUM2 -> KeyEvent.KEYCODE_2
            Key.NUM3 -> KeyEvent.KEYCODE_3
            Key.NUM4 -> KeyEvent.KEYCODE_4
            Key.NUM5 -> KeyEvent.KEYCODE_5
            Key.NUM6 -> KeyEvent.KEYCODE_6
            Key.NUM7 -> KeyEvent.KEYCODE_7
            Key.NUM8 -> KeyEvent.KEYCODE_8
            Key.NUM9 -> KeyEvent.KEYCODE_9
            Key.A -> KeyEvent.KEYCODE_A
            Key.B -> KeyEvent.KEYCODE_B
            Key.C -> KeyEvent.KEYCODE_C
            Key.D -> KeyEvent.KEYCODE_D
            Key.E -> KeyEvent.KEYCODE_E
            Key.F -> KeyEvent.KEYCODE_F
            Key.G -> KeyEvent.KEYCODE_G
            Key.H -> KeyEvent.KEYCODE_H
            Key.I -> KeyEvent.KEYCODE_I
            Key.J -> KeyEvent.KEYCODE_J
            Key.K -> KeyEvent.KEYCODE_K
            Key.L -> KeyEvent.KEYCODE_L
            Key.M -> KeyEvent.KEYCODE_M
            Key.N -> KeyEvent.KEYCODE_N
            Key.O -> KeyEvent.KEYCODE_O
            Key.P -> KeyEvent.KEYCODE_P
            Key.Q -> KeyEvent.KEYCODE_Q
            Key.R -> KeyEvent.KEYCODE_R
            Key.S -> KeyEvent.KEYCODE_S
            Key.T -> KeyEvent.KEYCODE_T
            Key.U -> KeyEvent.KEYCODE_U
            Key.V -> KeyEvent.KEYCODE_V
            Key.W -> KeyEvent.KEYCODE_W
            Key.X -> KeyEvent.KEYCODE_X
            Key.Y -> KeyEvent.KEYCODE_Y
            Key.Z -> KeyEvent.KEYCODE_Z
            Key.LEFT_OS -> KeyEvent.KEYCODE_WINDOW
            Key.RIGHT_OS -> KeyEvent.KEYCODE_WINDOW
            Key.NUMPAD0 -> KeyEvent.KEYCODE_NUMPAD_0
            Key.NUMPAD1 -> KeyEvent.KEYCODE_NUMPAD_1
            Key.NUMPAD2 -> KeyEvent.KEYCODE_NUMPAD_2
            Key.NUMPAD3 -> KeyEvent.KEYCODE_NUMPAD_3
            Key.NUMPAD4 -> KeyEvent.KEYCODE_NUMPAD_4
            Key.NUMPAD5 -> KeyEvent.KEYCODE_NUMPAD_5
            Key.NUMPAD6 -> KeyEvent.KEYCODE_NUMPAD_6
            Key.NUMPAD7 -> KeyEvent.KEYCODE_NUMPAD_7
            Key.NUMPAD8 -> KeyEvent.KEYCODE_NUMPAD_8
            Key.NUMPAD9 -> KeyEvent.KEYCODE_NUMPAD_9
            Key.MULTIPLY -> KeyEvent.KEYCODE_NUMPAD_MULTIPLY
            Key.ADD -> KeyEvent.KEYCODE_NUMPAD_ADD
            Key.SUBTRACT -> KeyEvent.KEYCODE_NUMPAD_SUBTRACT
            Key.DECIMAL_POINT -> KeyEvent.KEYCODE_NUMPAD_DOT
            Key.DIVIDE -> KeyEvent.KEYCODE_NUMPAD_DIVIDE
            Key.F1 -> KeyEvent.KEYCODE_F1
            Key.F2 -> KeyEvent.KEYCODE_F2
            Key.F3 -> KeyEvent.KEYCODE_F3
            Key.F4 -> KeyEvent.KEYCODE_F4
            Key.F5 -> KeyEvent.KEYCODE_F5
            Key.F6 -> KeyEvent.KEYCODE_F6
            Key.F7 -> KeyEvent.KEYCODE_F7
            Key.F8 -> KeyEvent.KEYCODE_F8
            Key.F9 -> KeyEvent.KEYCODE_F9
            Key.F10 -> KeyEvent.KEYCODE_F10
            Key.F11 -> KeyEvent.KEYCODE_F11
            Key.F12 -> KeyEvent.KEYCODE_F12
            Key.NUM_LOCK -> KeyEvent.KEYCODE_NUM_LOCK
            Key.SCROLL_LOCK -> KeyEvent.KEYCODE_SCROLL_LOCK
            Key.SEMI_COLON -> KeyEvent.KEYCODE_SEMICOLON
            Key.EQUAL_SIGN -> KeyEvent.KEYCODE_EQUALS
            Key.COMMA -> KeyEvent.KEYCODE_COMMA
            Key.DASH -> KeyEvent.KEYCODE_MINUS
            Key.PERIOD -> KeyEvent.KEYCODE_PERIOD
            Key.FORWARD_SLASH -> KeyEvent.KEYCODE_SLASH
            Key.BRACKET_LEFT -> KeyEvent.KEYCODE_LEFT_BRACKET
            Key.BACK_SLASH -> KeyEvent.KEYCODE_BACKSLASH
            Key.BRACKET_RIGHT -> KeyEvent.KEYCODE_RIGHT_BRACKET
            Key.SINGLE_QUOTE -> KeyEvent.KEYCODE_APOSTROPHE
        }
    }


internal val Int.getKey: Key
    get() {
        return when (this) {
            KeyEvent.KEYCODE_BACK -> Key.BACKSPACE
            KeyEvent.KEYCODE_TAB -> Key.TAB
            KeyEvent.KEYCODE_ENTER -> Key.ENTER
            KeyEvent.KEYCODE_SHIFT_LEFT -> Key.SHIFT_LEFT
            KeyEvent.KEYCODE_CTRL_LEFT -> Key.CTRL_LEFT
            KeyEvent.KEYCODE_ALT_LEFT -> Key.ALT_LEFT
            KeyEvent.KEYCODE_SHIFT_RIGHT -> Key.SHIFT_RIGHT
            KeyEvent.KEYCODE_CTRL_RIGHT -> Key.CTRL_RIGHT
            KeyEvent.KEYCODE_ALT_RIGHT -> Key.ALT_RIGHT
            KeyEvent.KEYCODE_BREAK -> Key.PAUSE_BREAK
            KeyEvent.KEYCODE_CAPS_LOCK -> Key.CAPS_LOCK
            KeyEvent.KEYCODE_ESCAPE -> Key.ESCAPE
            KeyEvent.KEYCODE_PAGE_UP -> Key.PAGE_UP
            KeyEvent.KEYCODE_SPACE -> Key.SPACE
            KeyEvent.KEYCODE_PAGE_DOWN -> Key.PAGE_DOWN
            KeyEvent.KEYCODE_MOVE_END -> Key.END
            KeyEvent.KEYCODE_HOME -> Key.HOME
            KeyEvent.KEYCODE_DPAD_LEFT -> Key.ARROW_LEFT
            KeyEvent.KEYCODE_DPAD_UP -> Key.ARROW_UP
            KeyEvent.KEYCODE_DPAD_RIGHT -> Key.ARROW_RIGHT
            KeyEvent.KEYCODE_DPAD_DOWN -> Key.ARROW_DOWN
            KeyEvent.KEYCODE_INSERT -> Key.INSERT
            KeyEvent.KEYCODE_DEL -> Key.DELETE
            KeyEvent.KEYCODE_0 -> Key.NUM0
            KeyEvent.KEYCODE_1 -> Key.NUM1
            KeyEvent.KEYCODE_2 -> Key.NUM2
            KeyEvent.KEYCODE_3 -> Key.NUM3
            KeyEvent.KEYCODE_4 -> Key.NUM4
            KeyEvent.KEYCODE_5 -> Key.NUM5
            KeyEvent.KEYCODE_6 -> Key.NUM6
            KeyEvent.KEYCODE_7 -> Key.NUM7
            KeyEvent.KEYCODE_8 -> Key.NUM8
            KeyEvent.KEYCODE_9 -> Key.NUM9
            KeyEvent.KEYCODE_A -> Key.A
            KeyEvent.KEYCODE_B -> Key.B
            KeyEvent.KEYCODE_C -> Key.C
            KeyEvent.KEYCODE_D -> Key.D
            KeyEvent.KEYCODE_E -> Key.E
            KeyEvent.KEYCODE_F -> Key.F
            KeyEvent.KEYCODE_G -> Key.G
            KeyEvent.KEYCODE_H -> Key.H
            KeyEvent.KEYCODE_I -> Key.I
            KeyEvent.KEYCODE_J -> Key.J
            KeyEvent.KEYCODE_K -> Key.K
            KeyEvent.KEYCODE_L -> Key.L
            KeyEvent.KEYCODE_M -> Key.M
            KeyEvent.KEYCODE_N -> Key.N
            KeyEvent.KEYCODE_O -> Key.O
            KeyEvent.KEYCODE_P -> Key.P
            KeyEvent.KEYCODE_Q -> Key.Q
            KeyEvent.KEYCODE_R -> Key.R
            KeyEvent.KEYCODE_S -> Key.S
            KeyEvent.KEYCODE_T -> Key.T
            KeyEvent.KEYCODE_U -> Key.U
            KeyEvent.KEYCODE_V -> Key.V
            KeyEvent.KEYCODE_W -> Key.W
            KeyEvent.KEYCODE_X -> Key.X
            KeyEvent.KEYCODE_Y -> Key.Y
            KeyEvent.KEYCODE_Z -> Key.Z
            KeyEvent.KEYCODE_WINDOW -> Key.LEFT_OS
            KeyEvent.KEYCODE_NUMPAD_0 -> Key.NUMPAD0
            KeyEvent.KEYCODE_NUMPAD_1 -> Key.NUMPAD1
            KeyEvent.KEYCODE_NUMPAD_2 -> Key.NUMPAD2
            KeyEvent.KEYCODE_NUMPAD_3 -> Key.NUMPAD3
            KeyEvent.KEYCODE_NUMPAD_4 -> Key.NUMPAD4
            KeyEvent.KEYCODE_NUMPAD_5 -> Key.NUMPAD5
            KeyEvent.KEYCODE_NUMPAD_6 -> Key.NUMPAD6
            KeyEvent.KEYCODE_NUMPAD_7 -> Key.NUMPAD7
            KeyEvent.KEYCODE_NUMPAD_8 -> Key.NUMPAD8
            KeyEvent.KEYCODE_NUMPAD_9 -> Key.NUMPAD9
            KeyEvent.KEYCODE_NUMPAD_MULTIPLY -> Key.MULTIPLY
            KeyEvent.KEYCODE_NUMPAD_ADD -> Key.ADD
            KeyEvent.KEYCODE_NUMPAD_SUBTRACT -> Key.SUBTRACT
            KeyEvent.KEYCODE_NUMPAD_DOT -> Key.DECIMAL_POINT
            KeyEvent.KEYCODE_NUMPAD_DIVIDE -> Key.DIVIDE
            KeyEvent.KEYCODE_F1 -> Key.F1
            KeyEvent.KEYCODE_F2 -> Key.F2
            KeyEvent.KEYCODE_F3 -> Key.F3
            KeyEvent.KEYCODE_F4 -> Key.F4
            KeyEvent.KEYCODE_F5 -> Key.F5
            KeyEvent.KEYCODE_F6 -> Key.F6
            KeyEvent.KEYCODE_F7 -> Key.F7
            KeyEvent.KEYCODE_F8 -> Key.F8
            KeyEvent.KEYCODE_F9 -> Key.F9
            KeyEvent.KEYCODE_F10 -> Key.F10
            KeyEvent.KEYCODE_F11 -> Key.F11
            KeyEvent.KEYCODE_F12 -> Key.F12
            KeyEvent.KEYCODE_NUM_LOCK -> Key.NUM_LOCK
            KeyEvent.KEYCODE_SCROLL_LOCK -> Key.SCROLL_LOCK
            KeyEvent.KEYCODE_SEMICOLON -> Key.SEMI_COLON
            KeyEvent.KEYCODE_EQUALS -> Key.EQUAL_SIGN
            KeyEvent.KEYCODE_COMMA -> Key.COMMA
            KeyEvent.KEYCODE_MINUS -> Key.DASH
            KeyEvent.KEYCODE_PERIOD -> Key.PERIOD
            KeyEvent.KEYCODE_SLASH -> Key.FORWARD_SLASH
            KeyEvent.KEYCODE_LEFT_BRACKET -> Key.BRACKET_LEFT
            KeyEvent.KEYCODE_BACKSLASH -> Key.BACK_SLASH
            KeyEvent.KEYCODE_RIGHT_BRACKET -> Key.BRACKET_RIGHT
            KeyEvent.KEYCODE_APOSTROPHE -> Key.SINGLE_QUOTE
            else -> Key.ANY_KEY
        }
    }