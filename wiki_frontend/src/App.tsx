import { Card, Container, Grid, Paper, Typography, Stack, Box } from "@suid/material";
import styled from "@suid/material/styles/styled";
import Canvas from "./Canvas";
import Widgets from "./Widgets";

const Item = styled(Card)(({ theme }) => ({
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));

export default function BasicStack() {
  return (
    <Box sx={{
      display:"flex",
      justifyContent:"center",
      minHeight:"100vh"
    }}>
       <Grid
      container
      spacing={1}
      sx={{
        margin: "5px",
      }}
    >
      <Grid item xs={3}>
        <Widgets />
      </Grid>
      <Grid item xs={9}>
        <Canvas></Canvas>
      </Grid>
    </Grid>
    </Box>
  );
}
