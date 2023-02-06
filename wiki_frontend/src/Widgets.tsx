import {
  Button,
  Card,
  Container,
  Grid,
  Paper,
  Stack,
  Typography,
} from "@suid/material";
import AddIcon from "@suid/icons-material/Add";

export default function () {
  return (
    <Paper
      elevation={1}
      sx={{
        margin: "5%",
        padding: "5%",
      }}
    >
      <Typography>Widgets</Typography>
      <Grid container>
        <Grid item xs={6}>
          <Button
            sx={{
              padding: "5%",
            }}
          >
            <Stack alignItems={"center"} direction={"column"}>
              <AddIcon></AddIcon>
              <Typography>Node</Typography>
            </Stack>
          </Button>
        </Grid>
        <Grid item xs={6}>
          <Button
            sx={{
              padding: "5%",
            }}
          >
            <Stack alignItems={"center"} direction={"column"}>
              <AddIcon></AddIcon>
              <Typography>Connection</Typography>
            </Stack>
          </Button>
        </Grid>
      </Grid>
    </Paper>
  );
}
