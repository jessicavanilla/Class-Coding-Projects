library(tidyverse)

b <- billboard |>
  pivot_longer(cols=starts_with("wk"), names_to = "week",
               values_to = "rank", values_drop_na = TRUE) |>
  mutate(week = parse_number(week))

# which dates are represented?
range(b$date.entered)
sort(unique(b$date.entered))

# are all the weeks in 2000 present?
filter(b, year(date.entered) == 2000) |> 
  pull(date.entered) |> unique() |> sort() |> length()

b <- mutate(b, date = date.entered + weeks(week - 1))

# how many rankings are there on a particular date?
wk <- filter(b, year(date) == 2000) |>
  group_by(date) |>
  summarize(count = n())

# it does not seem we have the top 100 songs - data is not complete
ggplot(wk, aes(x = date, y = count)) +
  geom_line()

# do we have the top songs?
# when looking at all the rankings for Jan 15th, there are multiple positions missing from the ranks
b |>
  filter(year(date) == 2000, week(date) == 3) |>
  arrange(rank)
