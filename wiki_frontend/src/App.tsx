import { Card, Grid } from "@suid/material";
import styled from "@suid/material/styles/styled";
import Widgets from "./Widgets";

const Item = styled(Card)(({ theme }) => ({
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));

export default function BasicStack() {
  return (
    <Grid container>
      <Grid item xs={3}>
        <Widgets />
      </Grid>
      <Grid item xs={9}></Grid>
    </Grid>
  );
}
