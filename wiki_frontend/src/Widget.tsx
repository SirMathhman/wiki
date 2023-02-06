import {
  Box,
  Button,
  Card,
  Container,
  Grid,
  Paper,
  Stack,
  Typography,
} from "@suid/material";
import { JSXElement } from "solid-js";

export interface WidgetProps {
  children: JSXElement[];
}

export default function (props: WidgetProps) {
  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
      }}
    >
      <Button
        sx={{
          padding: "5%",
        }}
      >
        <Stack alignItems={"center"}>
          <>{props.children}</>
        </Stack>
      </Button>
    </Box>
  );
}
