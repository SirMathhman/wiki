import {
  Button,
  Card,
  Container,
  Divider,
  Grid,
  Paper,
  Stack,
  Typography,
} from "@suid/material";
import AddIcon from "@suid/icons-material/Add";
import Widget from "./Widget";
import { CloseFullscreen } from "@suid/icons-material";

export default function () {
  return (
    <Paper
      elevation={1}
      sx={{
        padding: "10px",
      }}
    >
      <Typography variant="h5">Tools</Typography>
      <Divider></Divider>
      <Grid container>
        <Grid item xs={6}>
          <Widget>
            <AddIcon />
            <Typography>Node</Typography>
          </Widget>
        </Grid>
        <Grid item xs={6}>
          <Widget>
            <CloseFullscreen />
            <Typography>Connection</Typography>
          </Widget>
        </Grid>
      </Grid>
    </Paper>
  );
}
