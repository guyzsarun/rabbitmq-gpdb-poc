import argparse

import json
import numpy as np
import pandas as pd

from datetime import date, timedelta

# TODO Add publish data
def generate_json(dataframe):
    for index, row in dataframe.iterrows():
        data = {
            "temperature": row["temperature"],
            "temperature_2": row["temperature_2"],
            "humidity": row["humidity"],
            "pm2.5": row["pm25"],
            "power": row["power"],
        }
        json_dump = json.dumps(data)
        print(json_dump)


def main(timeslot, basetmp, csv):

    sdate = date(1997, 3, 22)  # start date
    edate = date(2020, 4, 9)  # end date

    index = pd.date_range(sdate, edate - timedelta(days=1), freq="H")

    time = np.sin(np.arange(0, timeslot, 1))
    pm25 = np.random.normal(90, 15, timeslot)
    humid = np.random.normal(50, 15, timeslot)

    temp2_fluc = np.random.normal(10, 3, timeslot)
    power_fluc = np.random.normal(50, 1, timeslot)

    # Add seasonal data
    max_temp = 1
    seasonal = np.arange(0, max_temp, max_temp / timeslot)
    seasonal_pm = np.arange(0, 10, 10 / timeslot)
    temp_flux = np.random.rand(len(time)) * (3)

    temp = temp_flux + basetmp + seasonal
    temp2_fluc = temp - temp2_fluc
    pm25 = pm25 + seasonal_pm
    power = power_fluc + temp

    date_index = index[: len(temp)]

    df = pd.DataFrame(date_index, columns=["timestamp"])
    df["temperature"] = pd.Series(temp, index=df.index)
    df["temperature_2"] = pd.Series(temp2_fluc, index=df.index)
    df["humidity"] = pd.Series(humid, index=df.index)
    df["pm25"] = pd.Series(pm25, index=df.index)
    df["power"] = pd.Series(power, index=df.index)

    if csv != None:
        df.to_csv(csv, index=False)

    generate_json(df)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Temperature, Humidity Data generator")

    parser.add_argument(
        "-t",
        "--timeslot",
        action="store",
        type=int,
        required=False,
        default=1000,
        help="Number of data point generated",
    )
    parser.add_argument(
        "-b",
        "--basetmp",
        action="store",
        type=int,
        required=False,
        default=30,
        help="Base temperature 1",
    )
    parser.add_argument(
        "--csv",
        action="store",
        type=str,
        required=False,
        help="Name of the csv file to genetate",
    )

    args = parser.parse_args()

    main(args.timeslot, args.basetmp, args.csv)
