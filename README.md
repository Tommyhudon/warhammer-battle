# warhammer-battle

Small project to compare units strength in warhammer AoS

# How to use

To run a simulation you need to use the end point POST/battle_report with a body.

##body exemple

[
     {
        "name": "Plague Monks",
        "number": 20,
        "save": 6,
        "wound": 1,
        "totalWounds": 20,
        "weapon" : {
            "attacks": 2,
            "toHit": 4,
            "toWound": 4,
            "rend": 0,
            "damage": 1
        },
        "reRoll" : {
                    "reRollHits": true,
                    "reRollWounds": false,
                    "reRollSaves": false
        }
    },
     {
        "name": "Skeletons",
        "number": 20,
        "save": 6,
        "wound": 1,
        "totalWounds": 20,
        "weapon" : {
            "attacks": 1,
            "toHit": 4,
            "toWound": 4,
            "rend": 0,
            "damage": 1
        },
        "reRoll" : {
            "reRollHits": false,
            "reRollWounds": false,
            "reRollSaves": false
        }
    }
]
