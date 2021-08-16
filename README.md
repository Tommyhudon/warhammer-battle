# warhammer-battle

Small project to compare units strength in warhammer AoS

# How to use

To run a simulation you need to use the end point POST/battle_report with a body.

##body exemple
```json
[
    {
        "name": "Liberators",
        "number": 10,
        "save": 4,
        "wound": 2,
        "totalWounds": 20,
        "weapon" : {
                    "attacks": 2,
                    "toHit": 4,
                    "toWound": 3,
                    "rend": 0,
                    "damage": 1
        },
        "abilities" : {
                    "reRollAllFailHits": false,
                    "reRollAllFailWounds": false,
                    "reRollAllFailSaves": false,
                    "reRollSavesOn": 1
        }
    },
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
        "abilities" : {
                    "reRollAllFailHits": true,
                    "reRollAllFailWounds": false,
                    "reRollAllFailSaves": false
        }
    }
]
```
